package com.rishab.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public boolean addMembershipAccount() {
        System.out.println(getClass() + ": just added a membership account.");
        return true;
    }

    @Override
    public void logMembership() {
        System.out.println(getClass() + ": logging membership account.");
    }
}
