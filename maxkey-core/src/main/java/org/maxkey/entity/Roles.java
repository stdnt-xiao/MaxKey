/*
 * Copyright [2020] [MaxKey of copyright http://www.maxkey.top]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.maxkey.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.mybatis.jpa.persistence.JpaBaseEntity;
import org.hibernate.validator.constraints.Length;
@Entity
@Table(name = "MXK_ROLES")
public class Roles extends JpaBaseEntity implements Serializable {

    private static final long serialVersionUID = 4660258495864814777L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "snowflakeid")
    String id;

    @Length(max = 60)
    @Column
    String roleCode;
    
    @Length(max = 60)
    @Column
    String roleName;
    
    @Column
    String dynamic;

    @Column
    String filters ;
    
    @Column
    String orgIdsList;
    @Column
    String resumeTime; 
    @Column
    String suspendTime;
    
    @Column
    int isdefault;
    @Column
    String description;
    @Column
    String createdBy;
    @Column
    String createdDate;
    @Column
    String modifiedBy;
    @Column
    String modifiedDate;
    @Column
    int status;
    
	@Column
	private String instId;

	private String instName;

    public Roles() {
    }

    public Roles(String id) {
        this.id = id;
    }

    /**
     * Groups.
     * @param id String
     * @param name String
     * @param isdefault int
     */
    public Roles(String id,String roleCode, String roleName, int isdefault) {
        super();
        this.id = id;
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.isdefault = isdefault;
    }

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
    public int getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(int isdefault) {
        this.isdefault = isdefault;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * ROLE_ALL_USER must be 
     * 		1, dynamic 
     * 		2, all orgIdsList 
	 *		3, not filters
     */
    public void setDefaultAllUser() {
    	this.dynamic = "1";
    	this.orgIdsList ="";
		this.filters ="";
    }
    
    public String getDynamic() {
        return dynamic;
    }

    public void setDynamic(String dynamic) {
        this.dynamic = dynamic;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }

    public String getOrgIdsList() {
        return orgIdsList;
    }

    public void setOrgIdsList(String orgIdsList) {
        this.orgIdsList = orgIdsList;
    }

    public String getResumeTime() {
        return resumeTime;
    }

    public void setResumeTime(String resumeTime) {
        this.resumeTime = resumeTime;
    }

    public String getSuspendTime() {
        return suspendTime;
    }

    public void setSuspendTime(String suspendTime) {
        this.suspendTime = suspendTime;
    }

    public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Roles [id=");
		builder.append(id);
		builder.append(", roleCode=");
		builder.append(roleCode);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", dynamic=");
		builder.append(dynamic);
		builder.append(", filters=");
		builder.append(filters);
		builder.append(", orgIdsList=");
		builder.append(orgIdsList);
		builder.append(", resumeTime=");
		builder.append(resumeTime);
		builder.append(", suspendTime=");
		builder.append(suspendTime);
		builder.append(", isdefault=");
		builder.append(isdefault);
		builder.append(", description=");
		builder.append(description);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", modifiedBy=");
		builder.append(modifiedBy);
		builder.append(", modifiedDate=");
		builder.append(modifiedDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", instId=");
		builder.append(instId);
		builder.append(", instName=");
		builder.append(instName);
		builder.append("]");
		return builder.toString();
	}

}
