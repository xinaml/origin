package com.bjike.to.user;

import com.bjike.common.aspect.ADD;
import com.bjike.common.aspect.EDIT;
import com.bjike.to.BaseTO;
import com.bjike.type.user.RelationshipType;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 17:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RecommendTO extends BaseTO {

    //真实姓名
    @NotBlank(message = "真实姓名不能为空!", groups = {ADD.class, EDIT.class})
    private String realName;

    //联系号码
    @NotBlank(message = "联系号码不能为空!", groups = {ADD.class, EDIT.class})
    private String telephone;

    //兴趣爱好
    @NotBlank(message = "兴趣不能为空!", groups = {ADD.class, EDIT.class})
    private String interest;

    //地址
    @NotBlank(message = "地址不能为空!", groups = {ADD.class, EDIT.class})

    private String address;
    //关系
    @NotNull(message = "地址不能关系!", groups = {ADD.class, EDIT.class})
    private RelationshipType relationshipType;


    //生日
    private String birthday;

    //性格
    private String disposition;

    //籍贯
    private String nativePlace;

    //毕业学校
    private String school;

    //学历
    @NotBlank(message = "学历不能关系!", groups = {ADD.class, EDIT.class})
    private String education;

    //公司
    private String company;

    //职位
    private String job;

    //qq
    private String qq;

    //邮箱
    private String email;

    //微信
    private String weChat;

    //父亲姓名
    private String fatherName;

    //母亲姓名
    private String motherName;

    //婚姻状况
    private Boolean marriage;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RelationshipType getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(RelationshipType relationshipType) {
        this.relationshipType = relationshipType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDisposition() {
        return disposition;
    }

    public void setDisposition(String disposition) {
        this.disposition = disposition;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Boolean getMarriage() {
        return marriage;
    }

    public void setMarriage(Boolean marriage) {
        this.marriage = marriage;
    }
}
