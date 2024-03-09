package fctk.server.repositories.interfaces;

import fctk.server.entities.GroupDTO;
import fctk.server.exceptions.RepositoryException;

import java.util.List;

public interface IGroupRepository {

    long add(GroupDTO t) throws RepositoryException;

    Long update(GroupDTO t) throws RepositoryException;

    void deleteById(long id) throws RepositoryException;

    GroupDTO getById(long id) throws RepositoryException;

    List<GroupDTO> getAll() throws RepositoryException;
}
