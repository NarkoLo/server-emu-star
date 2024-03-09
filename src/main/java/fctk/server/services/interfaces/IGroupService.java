package fctk.server.services.interfaces;

import fctk.server.entities.GroupDTO;
import fctk.server.exceptions.ServiceException;

import java.util.List;

public interface IGroupService {
    long addGroup(GroupDTO group) throws ServiceException;

    void updateGroup(GroupDTO t) throws ServiceException;

    void deleteGroup(long i) throws ServiceException;

    GroupDTO getGroup(long i) throws ServiceException;

    List<GroupDTO> getGroups() throws ServiceException;

}
