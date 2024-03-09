package fctk.server.repositories;

import fctk.server.entities.GroupDTO;
import fctk.server.exceptions.RepositoryException;
import fctk.server.repositories.dbentities.GroupDB;
import fctk.server.repositories.interfaces.IGroupRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupRepository implements IGroupRepository {
    private final Map<Long, GroupDB> groupMap;

    public GroupRepository(DataBase dataBase) {
        this.groupMap = dataBase.groupsTable();
    }

    @Override
    public long add(GroupDTO group) {
        long currentId = !groupMap.isEmpty() ? Collections.max(groupMap.keySet()) + 1 : 1;
        groupMap.put(currentId, new GroupDB(group.name()));
        return currentId;
    }

    @Override
    public Long update(GroupDTO group) throws RepositoryException {
        Long groupId = group.id();
        if (groupMap.containsKey(groupId)) {
            groupMap.put(groupId, new GroupDB(group.name()));
            return groupId;
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        groupMap.remove(id);
    }

    @Override
    public GroupDTO getById(long id) {
        var group = groupMap.getOrDefault(id, null);
        if (group == null) {
            return null;
        } else {
            return new GroupDTO(id, group.name());
        }
    }

    @Override
    public List<GroupDTO> getAll() {
        return groupMap.entrySet().stream().map(entry -> new GroupDTO(entry.getKey(), entry.getValue().name())).collect(Collectors.toList());
    }
}
