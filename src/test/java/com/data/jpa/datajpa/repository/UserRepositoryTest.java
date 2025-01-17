package com.data.jpa.datajpa.repository;

import com.data.jpa.datajpa.domain.Address;
import com.data.jpa.datajpa.domain.Gender;
import com.data.jpa.datajpa.domain.User;
import com.data.jpa.datajpa.domain.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
//    @Transactional
    void crud() {
//        userRepository.save(new User());
//        userRepository.findAll().forEach(System.out::println);

//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
//        users.forEach(System.out::println);

//        User jack = new User("jack", "jack@naver");
//        User jason = new User("jason", "jason@naver");
//
//        userRepository.saveAll(Lists.newArrayList(jack, jason));
//        List<User> all = userRepository.findAll();
//        all.forEach(System.out::println);

//        User one = userRepository.getOne(1L);
//        System.out.println("one = " + one);

//        User user = userRepository.findById(1L).orElse(null);
//        System.out.println("user = " + user);

//        userRepository.saveAndFlush(new User("new martin", "newmartin@naver.com"));
//        userRepository.flush();

//        userRepository.findAll().forEach(System.out::println);

        /*
        long count = userRepository.count();
        System.out.println("count = " + count);

        boolean b = userRepository.existsById(1L);
        System.out.println("b = " + b);


        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));


//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 33L))); // -> 성능 안나옴. 이유는 처음에 모든 데이터를 조회하기때문
        // -> 그래서
//        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(1L, 33L)));
        userRepository.deleteAllInBatch();
        userRepository.findAll().forEach(System.out::println);


        // -> 페이징
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
        System.out.println("users = " + users);
        System.out.println("total elem : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numofElem : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size   : " + users.getSize());

        users.getContent().forEach(System.out::println);


        // -> qbe query by example
        ExampleMatcher macher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());

        Example<User> example = Example.of(new User("ma", "fastcampus.com"), macher);

        userRepository.findAll(example).forEach(System.out::println);



        User user = new User();
        user.setEmail("slow");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);
        userRepository.findAll(example).forEach(System.out::println);


         */

        userRepository.save(new User("david", "david@naver.com"));
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@nate.com");

        userRepository.save(user);
    }


    @Test
    void select() {
        /*
        System.out.println(userRepository.findByName("dennis"));
        System.out.println("userRepository.findByEmail(\"martin@fastcampus.com\") = " + userRepository.findByEmail("martin@fastcampus.com"));
        System.out.println("userRepository.getByEmail(\"martin@fastcampus.com\") = " + userRepository.getByEmail("martin@fastcampus.com"));
        System.out.println("userRepository.readByEmail(\"martin@fastcampus.com\") = " + userRepository.readByEmail("martin@fastcampus.com"));
        System.out.println("userRepository.queryByEmail(\"martin@fastcampus.com\") = " + userRepository.queryByEmail("martin@fastcampus.com"));
        System.out.println("userRepository.searchByEmail(\"martin@fastcampus.com\") = " + userRepository.searchByEmail("martin@fastcampus.com"));
        System.out.println("userRepository.streamByEmail(\"martin@fastcampus.com\") = " + userRepository.streamByEmail("martin@fastcampus.com"));
        System.out.println("userRepository.findUserByEmail(\"martin@fastcampus.com\") = " + userRepository.findUserByEmail("martin@fastcampus.com"));

        System.out.println("userRepository.findTop2ByName(\"martin\") = " + userRepository.findTop2ByName("martin"));
        System.out.println("userRepository.findFirst1ByName(\"martin\") = " + userRepository.findFirst1ByName("martin"));
        System.out.println("userRepository.findLast1ByName(\"martin\") = " + userRepository.findLast1ByName("martin"));


        System.out.println("userRepository = " + userRepository.findByEmailAndName("martin@fastcampus.com", "martin")) ;
        System.out.println("userRepository = " + userRepository.findByEmailOrName("martin@fastcampus.com", "martin2")) ;
        System.out.println("userRepository = " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L))) ;
        System.out.println("userRepository.findByIdAfter = " + userRepository.findByIdAfter(4L));
        System.out.println("userRepository.findByCreatedAtGreaterThan = " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("userRepository.findByCreatedAtGreaterThanEqual = " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("userRepository.findByCreatedAtBetween = " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("userRepository.findByIdBetween = " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L) = " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));


        System.out.println("userRepository.findByIdIsNotNull() = " + userRepository.findByIdIsNotNull());
        System.out.println("userRepository.findByIdIsNotEmpty() = " + userRepository.findByAddressIsNotEmpty());

        System.out.println("userRepository.findByNameIn(Lists.newArrayList(\"martin\", \"dennis\")) = " + userRepository.findByNameIn(Lists.newArrayList("martin", "dennis")));
        System.out.println("userRepository.findByNameStartingWith(\"mar\") = " + userRepository.findByNameStartingWith("mar"));
        System.out.println("userRepository.findByNameEndingWith(\"mar\") = " + userRepository.findByNameEndingWith("tin"));
        System.out.println("userRepository.findByNameContains(\"mar\") = " + userRepository.findByNameContains("ar"));
        System.out.println("userRepository.findByNameLike(\"%n2%\") = " + userRepository.findByNameLike("%n2%"));
         */

    }

    @Test
    void pagingAndSortingTest() {
        /*
        System.out.println("userRepository.findTop1ByNameOrderByIdDesc(\"martin\") = " + userRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println("userRepository.findFirstByNameOrderByIdDescEmailAsc(\"martin\") = " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));



//        System.out.println("userRepository.findFirstByName(\"martin\", Sort.by(Sort.Order.desc(\"id\"))) = " +
//                userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email") )));

        System.out.println("userRepository.findFirstByName(\"martin\", Sort.by(Sort.Order.desc(\"id\"))) = " +
                userRepository.findFirstByName("martin", getSort()));


         */

        System.out.println("userRepository.findByName(\"martin\",, PageRequest.of(0, 1, Sort.by(Sort.Order.desc(\"id\")))) = "
                + userRepository.findByName("martin", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());

    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();

        user.setName("martin");
        user.setEmail("hong@naver.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("hong");

        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);
        System.out.println("userRepository = " + userRepository.findRowRecord().get("gender"));
    }

    @Test
    void Listener() {
        User user = new User();
        user.setEmail("martin2@fatecampus.com");
        user.setName("martin");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrtin");

        userRepository.save(user2);
        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {

        User usr = userRepository.findByEmail("martin2@fastcampus.com");
        usr.setEmail("martin2@fastcampus.com");
        usr.setName("martin");
//        usr.setCreatedAt(LocalDateTime.now());
//        usr.setUpdatedAt(LocalDateTime.now());

        userRepository.save(usr);
        System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("user asis= " + user);

        user.setName("martin22");
        userRepository.save(user);

        System.out.println("user tobe= " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("martin-new@fastcamput.com");
        user.setName("martin-new");

        userRepository.save(user);

        user.setName("martin-new-new");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);


    }

    @Test
    void userRelationTest() {
        User user = new User();
        user.setName("david");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);

//        List<UserHistory> result = userHistoryRepository
//                .findByUserId(
//                        userRepository.findByEmail("daniel@fastcampus.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();
        result.forEach(System.out::println);



        System.out.println("UserHistory.getUser() : " + userHistoryRepository.findAll().get(0).getUser());
    }


    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                        Sort.Order.asc("email")
        );
    }



    @Test
    void embedTest() {
//        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시", "강남구", "강남대로 365 미왕빌딩", "06241"));
        user.setCompanyAddress(new Address("서울시", "성동구", "성수대로 3123", "06241"));

        userRepository.save(user);


        User user1 = new User();
        user1.setName("hoshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);
        userRepository.save(user1);

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());
        userRepository.save(user2);

//        em.clear();

//        userRepository.findAll().forEach(System.out::println);
//        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRowRecord().forEach(a -> System.out.println(a.values()));

        assertAll(
                () ->  assertThat(userRepository.findById(7L).get().getHomeAddress()).isNull(),
                () ->  assertThat(userRepository.findById(8L).get().getHomeAddress()).isInstanceOf(Address.class)
        );

    }
}
