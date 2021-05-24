package com.dphong.inflearnthejavatest.member;


import com.dphong.inflearnthejavatest.domain.Member;
import com.dphong.inflearnthejavatest.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void notify(Study newstudy);

    void validate(Long memberId);

}
