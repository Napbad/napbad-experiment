
package org.napbad.scoremanager.service.impl;

import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.Predicate;
import org.napbad.scoremanager.model.dto.score.ScoreCreateInput;
import org.napbad.scoremanager.model.dto.user.*;
import org.napbad.scoremanager.model.entity.*;
import org.napbad.scoremanager.service.UserService;
import org.napbad.scoremanager.util.SHA256Encryption;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl
        implements UserService {
    private final JSqlClient client;
    private final UserTable table;

    public UserServiceImpl(JSqlClient client) {
        this.client = client;
        this.table = UserTable.$;
    }

    public void register(UserRegisterInput user) {
        user.setPassword(SHA256Encryption.getSHA256((String) user.getPassword()));
        this.client.insert(user);
    }

    public UserLoginRes login(UserForLogin user) {
        user.setPassword(SHA256Encryption.getSHA256(user.getPassword()));
        List<UserLoginRes> res = this.client.createQuery(this.table)
                .where(this.table.identifier().eq(user.getIdentifier()))
                .where(new Predicate[]{this.table.password().eq(user.getPassword())})
                .select(this.table.fetch(UserLoginRes.class)).execute();
        if (res == null || res.isEmpty()) {
            throw new RuntimeException("用户名或密码错误");
        }
        return (UserLoginRes) res.getFirst();
    }

    public void deleteUser(UserInputDelete user) {
        this.client.createDelete(this.table).where(new Predicate[]{this.table.getId().eq(user.getId())}).execute();
    }

    @Override
    public List<User> generate(Integer number) {
        List<User> users = new ArrayList<>();

        List<Class_> list = client.createQuery(Class_Table.$).select(Class_Table.$).execute();

        List<User> list1 = client.createQuery(table).orderBy(table.id().desc()).select(table).limit(1).execute();
        int maxUserId = list1.isEmpty() ? 0 : (int) list1.get(0).id();

        for (int i = 0; i < number; i++) {
            User user = generateSingleStudent(maxUserId + i, list);
            users.add(user);
        }


        // 将插入的用户转换为查询结果
        return users;
    }

    @Override
    public List<UserInfo_student> query(UserSpecification user) {

        return client.createQuery(
                        table
                ).where(
                        user
                )
                .select(
                        table.fetch(
                                UserInfo_student.class
                        )
                ).execute();

    }

    private User generateSingleStudent(int index, List<Class_> classes) {

        List<Class_> randomClasses = getRandomClasses(3, classes);
        User input = UserDraft.$.produce(
                user -> {
                    user.setName("Generated Student " + index);
                    user.setEmail("student" + index + "@example.com");
                    user.setIdentifier((long) index + 100000);
                    user.setPassword(SHA256Encryption.getSHA256("password"));
                    user.setUserRole(3);
                    user.setGender("Male");


                }
        );

        User user = client.insert(input).getModifiedEntity();


        for (Class_ clazz : randomClasses) {
            client.createUpdate(Class_Table.$)
                    .where(Class_Table.$.id().eq(clazz.id()))
                    .set(Class_Table.$.totalStudents(), clazz.totalStudents() + (1));

        }
        UserClassRelation relation = new UserClassRelation();
        relation.setId(user.id());
        relation.setClasses(
                new ArrayList<>() {
                    {
                        for (Class_ clazz : randomClasses) {
                            add(new UserClassRelation.TargetOf_classes(clazz));
                        }
                    }
                }
        );

        for (Class_ clazz : randomClasses){
            ScoreCreateInput score = new ScoreCreateInput();
            score.setStudentId(user.id());
            score.setClassesId(clazz.id());
            score.setRegularScore(80 + (new Random()).nextInt(index + 1) % 20);
            score.setMidtermScore(70 + (new Random()).nextInt(index + 1) % 20);
            score.setLabScore(60 + (new Random()).nextInt(index + 1) % 20);
            score.setFinalScore(90 + (new Random()).nextInt(index + 1) % 20);
            score.setTotalScore(score.getRegularScore() + score.getMidtermScore() + score.getLabScore() + score.getFinalScore());
            client.insert(score);

        }

        return user;
    }

    private List<Class_> getRandomClasses(int num, List<Class_> classes){
        List<Class_> result = new ArrayList<>();
        ArrayList<Integer> exists = new ArrayList();
        for (int i = 0; i < num; i++) {
            int randomIndex = (int) (Math.random() * classes.size());
            for (Integer integer : exists) {
                if (integer.equals(randomIndex)) {
                    i--;
                    break;
                }
            }
            exists.add(i);
            result.add(classes.get(randomIndex));
        }
        return result;
    }
}

