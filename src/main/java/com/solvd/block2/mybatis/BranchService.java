package com.solvd.block2.mybatis;

import com.solvd.block2.mappers.BranchEmployeeMapper;
import com.solvd.block2.mappers.BranchMapper;
import com.solvd.block2.sql.models.Branch;
import com.solvd.block2.sql.models.BranchEmployee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class BranchService {
    private final SqlSessionFactory sqlSessionFactory;

    public BranchService() {
        this.sqlSessionFactory = createSqlSessionFactory();
    }

    // Create session factory
    private SqlSessionFactory createSqlSessionFactory() {
        String resource = "mybatis/mybatis_config.xml";
        try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to create SqlSessionFactory.", e);
        }
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

    public void insertBranch(Branch branch) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            branchMapper.insertBranch(branch);
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

    public void deleteBranch(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchMapper branchMapper = sqlSession.getMapper(BranchMapper.class);
            branchMapper.deleteBranch(id);
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

    public void insertBranchEmployee(BranchEmployee branchEmployee) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            branchEmployeeMapper.insertBranchEmployee(branchEmployee);
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

    public void deleteBranchEmployee(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BranchEmployeeMapper branchEmployeeMapper = sqlSession.getMapper(BranchEmployeeMapper.class);
            branchEmployeeMapper.deleteBranchEmployee(id);
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




