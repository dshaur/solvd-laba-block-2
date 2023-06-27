package com.solvd.block2.sql.services;

import com.solvd.block2.mappers.BranchEmployeeMapper;
import com.solvd.block2.mappers.BranchMapper;
import com.solvd.block2.mybatis.MyBatisSessionFactory;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

public class MyBatisBranchService implements IBranchService {
    private final SqlSessionFactory sqlSessionFactory;

    public MyBatisBranchService() {
        this.sqlSessionFactory = MyBatisSessionFactory.getSqlSessionFactory();
    }

    // Service Methods for Branch
    public Branch getBranchById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            return branchMapper.getBranchById(id);
        }
    }

    public List<Branch> getAllBranches() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            return branchMapper.getAllBranches();
        }
    }

    @Override
    public BranchEmployee getBranchEmployeesById(int id) throws SQLException {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            return branchEmployeeMapper.getBranchEmployeeById(id);
        }
    }

    public void createBranch(Branch branch) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            branchMapper.createBranch(branch);
            sqlSession.commit();
        }
    }

    public void updateBranch(Branch branch) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            branchMapper.updateBranch(branch);
            sqlSession.commit();
        }
    }

    public void deleteBranch(Branch branch) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            branchMapper.deleteBranch(branch);
            sqlSession.commit();
        }
    }


    public List<Branch> getBranchesByLocation(String location) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            return branchMapper.getBranchesByLocation(location);
        }
    }

    // Service Methods for BranchEmployee
    public BranchEmployee getBranchEmployeeById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            return branchEmployeeMapper.getBranchEmployeeById(id);
        }
    }

    public List<BranchEmployee> getAllBranchEmployees() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            return branchEmployeeMapper.getAllBranchEmployees();
        }
    }


    public void createBranchEmployee(BranchEmployee branchEmployee) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            branchEmployeeMapper.createBranchEmployee(branchEmployee);
            sqlSession.commit();
        }
    }

    public void updateBranchEmployee(BranchEmployee branchEmployee) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            branchEmployeeMapper.updateBranchEmployee(branchEmployee);
            sqlSession.commit();
        }
    }

    public void deleteBranchEmployee(BranchEmployee branchEmployee) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            branchEmployeeMapper.deleteBranchEmployee(branchEmployee);
            sqlSession.commit();
        }
    }

    public List<BranchEmployee> getBranchEmployeesByBranchId(int branchId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            return branchEmployeeMapper.getBranchEmployeesByBranchId(branchId);
        }
    }


}




