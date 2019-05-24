package com.bksoftware.service.user;

import com.bksoftware.entities.user.AppUser;
import com.bksoftware.entities.user.Link;

import java.util.List;

public interface LinkService {

    List<Link> findByAppUser(AppUser appUser);

    boolean saveLink(Link link);

    boolean deleteLink(Link link);
}
