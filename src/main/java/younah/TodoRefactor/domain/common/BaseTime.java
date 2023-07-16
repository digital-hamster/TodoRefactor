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
public class BaseTime {
        @CreatedDate //인식 안먹는뎁쇼 ...
        @Column(name = "created_at")
        private LocalDateTime createdAt = LocalDateTime.now();

        @LastModifiedDate
        @Column(name = "modified_at")
        private LocalDateTime modifiedAt;

        @Column(name= "deleted_at")
        private LocalDateTime deletedAt;


        public void updateModifiedAt(){
            this.modifiedAt = LocalDateTime.now();
        }
}
