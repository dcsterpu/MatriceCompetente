package com.expleo.webcm.service;

import com.expleo.webcm.entity.expleodb.Skill;
import com.expleo.webcm.entity.expleodb.UserExpleo;
import com.expleo.webcm.entity.expleodb.UserSkill;

import java.util.List;

public interface SearchService {
    List<UserExpleo> searchUser(String text);
    List<Skill> searchPrincipalSkill(String cauta, int principalId);
    List<UserExpleo> searchUsersNotInProject(String codProiect, String searchTerm);
    List<Skill> searchSkillsNotInProject(String codProiect, String searchTerm);
    List<UserSkill> searchSkillWithEvaluation(String text, int eval);
}
