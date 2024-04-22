package com.huneth.hams.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 사용자/권한 관계 Entity
 */

// @Data는 @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode을 한꺼번에 설정해줌.
// @ToString은 클래스명(필드1명=필드1값,필드2명=필드2값,...) 식으로 출력
// @ToString(exclude=필드명) 위 출력에서 제외시킨다.(json 무한 루프 방지??)

@Data
@Entity
@ToString(exclude = {"user", "role"})
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp creationDate;

    @UpdateTimestamp
    private Timestamp lastUpdateDate;
}
