package com.neuporfolio.server.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生的作答
 *
 * @TableName sheet
 */
@TableName(value = "sheet")
@Data
public class Sheet implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     *
     */
    @TableField(value = "student_id")
    private Integer studentId;
    /**
     *
     */
    @TableField(value = "homework_id")
    private Integer homeworkId;
    /**
     *
     */
    @TableField(value = "mark")
    private Boolean mark;
    /**
     *
     */
    @TableField(value = "detail")
    private String detail;
    /**
     *
     */
    @TableField(value = "imgs")
    private String imgs;
    /**
     *
     */
    @TableField(value = "files")
    private String files;
    /**
     *
     */
    @TableField(value = "time")
    private Date time;

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
        Sheet other = (Sheet) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
                && (this.getHomeworkId() == null ? other.getHomeworkId() == null : this.getHomeworkId().equals(other.getHomeworkId()))
                && (this.getMark() == null ? other.getMark() == null : this.getMark().equals(other.getMark()))
                && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
                && (this.getImgs() == null ? other.getImgs() == null : this.getImgs().equals(other.getImgs()))
                && (this.getFiles() == null ? other.getFiles() == null : this.getFiles().equals(other.getFiles()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getHomeworkId() == null) ? 0 : getHomeworkId().hashCode());
        result = prime * result + ((getMark() == null) ? 0 : getMark().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getImgs() == null) ? 0 : getImgs().hashCode());
        result = prime * result + ((getFiles() == null) ? 0 : getFiles().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", homeworkId=").append(homeworkId);
        sb.append(", mark=").append(mark);
        sb.append(", detail=").append(detail);
        sb.append(", imgs=").append(imgs);
        sb.append(", files=").append(files);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}