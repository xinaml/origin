package com.bjike.entity.user;

import com.bjike.entity.BaseEntity;

import javax.persistence.*;

/**
 * @Author: [liguiqin]
 * @Date: [2017-08-23 09:36]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Entity
@Table(name = "user_info")
public class UserInfo extends BaseEntity {

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",unique = true,columnDefinition = "VARCHAR(36) COMMENT '用户id' ", nullable = false)
    private User user;
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '经验值'")
    private Double experience;
    @Column(columnDefinition = "DECIMAL(5,2) COMMENT '贡献值'")
    private Double contribute;
    @Column(columnDefinition = "DECIMAL(3,2) COMMENT '信誉值'")
    private Double reputation;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getContribute() {
        return contribute;
    }

    public void setContribute(Double contribute) {
        this.contribute = contribute;
    }

    public Double getReputation() {
        return reputation;
    }

    public void setReputation(Double reputation) {
        this.reputation = reputation;
    }

}
