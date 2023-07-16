package younah.TodoRefactor.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTime { // BaseTimeEntity or BaseEntity 가 보기 좋아보임 
        @CreatedDate //인식 안먹는뎁쇼 ... 빼세요
        @Column(name = "created_at")
        private LocalDateTime createdAt = LocalDateTime.now();

        @LastModifiedDate // 뺴세요
        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;

        @Column(name= "deleted_at")
        private LocalDateTime deletedAt; // 빼세요


        public void updateModifiedAt(){ // 지우세요
            this.modifiedAt = LocalDateTime.now();
        }


        // 추가본 이걸 쓰세요
        @PrePersist
        void prePersist() {
            this.createdAt = LocalDateTime.now();
            this.modifiedAt = LocalDateTime.now();
        }

        @PreUpdate
        void preUpdate() {
            this.modifiedAt = LocalDateTime.now();
        }
}
