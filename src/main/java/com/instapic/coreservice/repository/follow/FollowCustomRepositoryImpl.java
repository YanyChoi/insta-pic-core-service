package com.instapic.coreservice.repository.follow;

import com.instapic.coreservice.domain.QFollow;
import com.instapic.coreservice.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.instapic.coreservice.domain.QFollow.follow;
import static com.instapic.coreservice.domain.QUserInfo.userInfo;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public void deleteByUserIdAndTargetId(Long userId, Long targetId) {
        jpaQueryFactory.delete(follow)
                .where(follow.user.userId.eq(userId)
                        .and(follow.target.userId.eq(targetId)))
                .execute();
    }

    @Override
    public List<UserInfo> findTargetsByUserId(Long userId, Optional<Long> lastTargetId, int size) {
        if (lastTargetId.isPresent()) {
            return jpaQueryFactory.select(follow.target)
                    .from(follow)
                    .join(follow.target, userInfo)
                    .where(follow.user.userId.eq(userId)
                            .and(follow.target.userId.gt(lastTargetId.get())))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.select(follow.target)
                .from(follow)
                .join(follow.target, userInfo)
                .where(follow.user.userId.eq(userId))
                .limit(size)
                .fetch();
    }

    @Override
    public List<UserInfo> findUsersByTargetId(Long targetId, Optional<Long> lastUserId, int size) {
        if (lastUserId.isPresent()) {
            return jpaQueryFactory.select(follow.user)
                    .from(follow)
                    .join(follow.user, userInfo)
                    .where(follow.target.userId.eq(targetId)
                            .and(follow.user.userId.gt(lastUserId.get())))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.select(follow.user)
                .from(follow)
                .join(follow.user, userInfo)
                .where(follow.target.userId.eq(targetId))
                .limit(size)
                .fetch();
    }

    @Override
    public List<UserInfo> findMutualFollowers(String username, Long targetId, Optional<Long> lastUserId, int size) {
        QFollow follow1 = new QFollow("follow1");
        QFollow follow2 = new QFollow("follow2");

        if (lastUserId.isPresent()) {
            return jpaQueryFactory.select(follow1.target)
                    .from(follow1)
                    .join(follow2)
                    .on(follow1.target.userId.eq(follow2.user.userId))
                    .where(follow1.user.userName.eq(username)
                            .and(follow2.target.userId.eq(targetId))
                            .and(follow1.target.userId.gt(lastUserId.get())))
                    .limit(size)
                    .fetch();
        }
        return jpaQueryFactory.select(follow1.target)
                .from(follow1)
                .join(follow2)
                .on(follow1.target.userId.eq(follow2.user.userId))
                .where(follow1.user.userName.eq(username)
                        .and(follow2.target.userId.eq(targetId)))
                .limit(size)
                .fetch();
    }
}
