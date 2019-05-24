package com.bksoftware.service_impl.user;

import com.bksoftware.entities.user.AppUser;
import com.bksoftware.entities.user.Link;
import com.bksoftware.repository.user.LinkRepository;
import com.bksoftware.service.user.LinkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LinkService_Impl implements LinkService {

    private final static Logger LOGGER = Logger.getLogger(LinkService_Impl.class.getName());

    private final LinkRepository linkRepository;

    public LinkService_Impl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public List<Link> findByAppUser(AppUser appUser) {
        try {
            return linkRepository.findAllByAppUser(appUser);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-role-error : {0}", ex.getCause());
        }
        return new ArrayList<>();
    }

    @Override
    public boolean saveLink(Link link) {
        try {
            linkRepository.save(link);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-link-error : {0}", ex.getCause());
            return false;
        }
    }

    @Override
    public boolean deleteLink(Link link) {
        try {
            link.setStatus(false);
            linkRepository.save(link);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "save-link-error : {0}", ex.getCause());
            return false;
        }
    }
}
