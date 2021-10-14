package com.neuporfolio.server.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 存储有几种role
 *
 * @TableName identity
 */
@TableName(value = "identity")
@Data
public class Identity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * ROLE_xxx spring security style
     */
    @TableId
    private String role;
    /**
     * without spring security style
     */
    private String simplyRole;
    /**
     *
     */
    private String extra1;
    /**
     *
     */
    private String extra2;
    /**
     *
     */
    private String extra3;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Identity other = (Identity) that;
        return (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
                && (this.getSimplyRole() == null ? other.getSimplyRole() == null : this.getSimplyRole().equals(other.getSimplyRole()))
                && (this.getExtra1() == null ? other.getExtra1() == null : this.getExtra1().equals(other.getExtra1()))
                && (this.getExtra2() == null ? other.getExtra2() == null : this.getExtra2().equals(other.getExtra2()))
                && (this.getExtra3() == null ? other.getExtra3() == null : this.getExtra3().equals(other.getExtra3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getSimplyRole() == null) ? 0 : getSimplyRole().hashCode());
        result = prime * result + ((getExtra1() == null) ? 0 : getExtra1().hashCode());
        result = prime * result + ((getExtra2() == null) ? 0 : getExtra2().hashCode());
        result = prime * result + ((getExtra3() == null) ? 0 : getExtra3().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", role=").append(role);
        sb.append(", simplyRole=").append(simplyRole);
        sb.append(", extra1=").append(extra1);
        sb.append(", extra2=").append(extra2);
        sb.append(", extra3=").append(extra3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}