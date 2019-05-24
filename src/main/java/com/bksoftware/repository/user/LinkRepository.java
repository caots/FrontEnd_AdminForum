package com.bksoftware.repository.user;

import com.bksoftware.entities.user.AppUser;
import com.bksoftware.entities.user.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {

    List<Link> findAllByAppUser(AppUser appUser);

}
