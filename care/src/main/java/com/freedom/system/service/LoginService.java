package com.freedom.system.service;

import com.freedom.common.helper.DateHelper;
import com.freedom.common.model.ResultModel;
import com.freedom.system.entity.crud.UserEntity;
import com.freedom.system.model.ChangePasswordModel;
import com.freedom.system.model.LoginModel;
import com.freedom.system.model.UserModel;
import com.freedom.system.repository.crud.UserRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Value("${freedom.default.password:123456}")
    private String defaultPassword;

    @Value("${freedom.default.max-try-count:5}")
    private int maxTryCount;

    @Resource
    private UserRepository userRepository;

    /**
     * 用户登录
     * @param model 登录信息
     * @return 结果
     */
    @Transactional
    public ResultModel login(LoginModel model) {
        UserEntity entity = userRepository.findByNo(model.getUsername());

        // 如果 admin 用户不存在，自动创建。
        if (entity == null && "admin".equals(model.getUsername())) {
            // 不存在admin用户，自动创建admin用户
            entity = createUserEntity(
                    "admin",
                    "超级管理员",
                    "ROLE_ADMIN");
            userRepository.save(entity);
        }

        // 用户不存在
        if (entity == null) {
            return ResultModel.error("用户名或密码错误");
        }

        // 账号未启用
        if ("0".equals(entity.getStatus())) {
            return ResultModel.error("用户名或密码错误");
        }

        // 检查密码尝试次数
        int tryCount = entity.getTryCount();
        if (tryCount >= maxTryCount) {
            if (DateHelper.getDaysBetween(entity.getTryTime(), DateHelper.getNow()) < 1) {
                return ResultModel.error("账号被锁定，请24小时以后再试！");
            } else {
                tryCount = 0;
            }
        }

        // 密码不一致
        if (!passwordEncoder.matches(model.getPassword(), entity.getPassword())) {

            userRepository.updateTryCount(entity.getId(), tryCount + 1, DateHelper.getNow());
            if (tryCount >= maxTryCount - 1) {
                return ResultModel.error("账号被锁定，请24小时以后再试！");
            } else {
                String msg = String.format("密码错误，您还有 %d 次尝试机会。",
                        (maxTryCount - tryCount - 1));
                return ResultModel.error(msg);
            }
        }

        // 判断是否需要复位 tryCount = 0
        if (entity.getTryCount() > 0) {
            userRepository.updateTryCount(entity.getId(), 0, null);
        }

        // 返回用户信息
        return ResultModel.data(UserModel.fromEntity(entity));
    }

    /**
     * 刷新Token
     * @param username
     * @return
     */
    public ResultModel refreshToken(String username) {
        UserEntity entity = userRepository.findByNo(username);
        // 用户不存在
        if (entity == null) {
            return ResultModel.error("用户名不存在");
        }
        // 返回用户信息
        return ResultModel.data(UserModel.fromEntity(entity));
    }

    /**
     * 修改密码
     * @param model 修改密码
     * @return 结果
     */
    @Transactional
    public ResultModel changePassword(ChangePasswordModel model) {
        UserEntity entity = userRepository.findByNo(model.getUsername());
        // 账号不存在
        // 账号无效
        // 密码不正确
        if (entity == null
                || !"1".equals(entity.getStatus())
                || !passwordEncoder.matches(model.getOldPassword(), entity.getPassword())) {
            return ResultModel.error("旧密码不正确");
        }
        String password = passwordEncoder.encode(model.getNewPassword());
        userRepository.changePassword(password, model.getUsername());
        return ResultModel.data("OK");
    }

    /**
     * 重置密码
     * @param model 登录模型
     * @return 结果模型
     */
    @Transactional
    public ResultModel resetPassword(LoginModel model) {
        UserEntity entity = userRepository.findByNo(model.getUsername());
        // 账号不存在
        if (entity == null) {
            return ResultModel.error("账号不存在");
        }
        String password = passwordEncoder.encode(model.getPassword());
        userRepository.changePassword(password, model.getUsername());
        return ResultModel.data("OK");
    }

    /**
     * 创建用户 Entity
     *
     * @param no    登录账号
     * @param name  登录姓名
     * @param role  角色
     * @return UserEntity
     */
    private UserEntity createUserEntity(String no, String name, String role) {
        UserEntity entity = new UserEntity();
        entity.setNo(no);
        entity.setName(name);
        entity.setPassword(passwordEncoder.encode(defaultPassword));
        entity.setStatus("1");  // 启用
        entity.setRole(role);
        entity.setCreateUser("system");
        entity.setCreateTime(DateHelper.getNow());
        return entity;
    }
}
