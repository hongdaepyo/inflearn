package com.dphong.inflearnthejavatest.study;

import com.dphong.inflearnthejavatest.domain.Member;
import com.dphong.inflearnthejavatest.domain.Study;
import com.dphong.inflearnthejavatest.member.MemberService;

import java.util.Optional;

public class StudyService {
    private final MemberService memberService;
    private final StudyRepository repository;

    public StudyService(MemberService memberService, StudyRepository repository) {
        assert memberService != null;
        assert repository != null;
        this.memberService = memberService;
        this.repository = repository;
    }

    public Study createNewStudy(Long memberId, Study study) {
        Optional<Member> member = memberService.findById(memberId);
        study.setOwnerId(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id: '" + memberId + "'")).getId());

        Study newstudy = repository.save(study);
        memberService.notify(newstudy);
        return newstudy;
    }

    public Study openStudy(Study study) {
        study.open();
        Study openedStudy = repository.save(study);
        memberService.notify(openedStudy);
        return openedStudy;
    }

    public void hi() {

    }
}
