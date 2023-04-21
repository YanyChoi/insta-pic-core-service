package com.instapic.coreservice.repository;

import com.instapic.coreservice.domain.QFollow;
import com.instapic.coreservice.domain.UserInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.instapic.coreservice.domain.QFollow.follow;
import static com.instapic.coreservice.domain.QUser.user;
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
    public List<UserInfo> findTargetsByUserId(Long userId, Long lastTargetId, int size) {
        return jpaQueryFactory.select(follow.target)
                .from(follow)
                .join(follow.target, userInfo)
                .join(follow.user, userInfo)
                .where(follow.user.userId.eq(userId)
                        .and(follow.target.userId.gt(lastTargetId)))
                .fetch();
    }

    @Override
    public List<UserInfo> findUsersByTargetId(Long targetId, Long lastUserId, int size) {
        return jpaQueryFactory.select(follow.user)
                .from(follow)
                .join(follow.target, userInfo)
                .join(follow.user, userInfo)
                .where(follow.target.userId.eq(targetId)
                        .and(follow.user.userId.gt(lastUserId)))
                .limit(size)
                .fetch();
    }

    @Override
    public List<UserInfo> findMutualFollowers(Long userId, Long targetId) {
        QFollow follow1 = new QFollow("follow1");
        QFollow follow2 = new QFollow("follow2");
        return jpaQueryFactory.select(follow1.target)
                .from(follow1)
                .join(follow2)
                .on(follow1.target.userId.eq(follow2.user.userId))
                .where(follow1.user.userId.eq(userId)
                        .and(follow2.target.userId.eq(targetId)))
                .fetch();
    }
}
