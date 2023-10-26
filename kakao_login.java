import java.lang.annotation.Inherited;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditListner.class)
@ToString(of = {"id", "email"})
@Table(name = "member")
public class User implements Auditable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @OnetoOne(fetch = LAZY, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();



    @Enumerated(STRING)
    private Role role;

    @Embedded
    private TimeEntity timeEntity;

    @Builder
    public User(String email, UserProfile userProfile, Role role, TimeEntity timeEntity)
    {
        this.email = email;
        this.userProfile = userProfile;
        this.role = role;
        this.timeEntity = timeEntity;
    }

}

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id", "nickName", "gender"})
public class UserProfile {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false)
    private String nickName;

    @Embedded
    private Address address;

    private String phoneNumber;

    @Enumerated(STRING)
    private Gender gender;

    @OnetoOne(mappedBy = "userProfile", fetch = LAZY)
    private User user;

    private int age;

    private String provider;
    private String providerId;

    private String imageUrl;
}
