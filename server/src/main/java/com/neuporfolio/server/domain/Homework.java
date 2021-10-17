package com.neuporfolio.server.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 作业的题目
 *
 * @TableName homework
 */
@TableName(value = "homework")
@Data
public class Homework implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "homeword_id", type = IdType.AUTO)
    private Integer homewordId;
    /**
     *
     */
    @TableField(value = "time")
    private Date time;
    /**
     * 所属于的课程的id
     */
    @TableField(value = "course_id")
    private Integer courseId;
    /**
     * 发布者的id
     */
    @TableField(value = "teacher_id")
    private Integer teacherId;
    /**
     * 作业标题
     */
    @TableField(value = "title")
    private String title;
    /**
     * 作业的内容
     */
    @TableField(value = "detail")
    private String detail;
    /**
     *
     */
    @TableField(value = "class_id")
    private Integer classId;
    /**
     *
     */
    @TableField(value = "class_name")
    private String className;

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
        Homework other = (Homework) that;
        return (this.getHomewordId() == null ? other.getHomewordId() == null : this.getHomewordId().equals(other.getHomewordId()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
                && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()))
                && (this.getTeacherId() == null ? other.getTeacherId() == null : this.getTeacherId().equals(other.getTeacherId()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
                && (this.getClassId() == null ? other.getClassId() == null : this.getClassId().equals(other.getClassId()))
                && (this.getClassName() == null ? other.getClassName() == null : this.getClassName().equals(other.getClassName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHomewordId() == null) ? 0 : getHomewordId().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        result = prime * result + ((getTeacherId() == null) ? 0 : getTeacherId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getClassId() == null) ? 0 : getClassId().hashCode());
        result = prime * result + ((getClassName() == null) ? 0 : getClassName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", homewordId=").append(homewordId);
        sb.append(", time=").append(time);
        sb.append(", courseId=").append(courseId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", title=").append(title);
        sb.append(", detail=").append(detail);
        sb.append(", classId=").append(classId);
        sb.append(", className=").append(className);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}