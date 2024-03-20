package com.example.SplitWise.mapper;

import com.example.SplitWise.dto.GroupResponseDTO;
import com.example.SplitWise.dto.UserFriendResponseDTO;
import com.example.SplitWise.dto.UserLoginResponseDTO;
import com.example.SplitWise.dto.UserResponseDTO;
import com.example.SplitWise.model.Group;
import com.example.SplitWise.model.User;

import java.util.ArrayList;
import java.util.List;

public class EntityDTOMapper {
    public static UserLoginResponseDTO toDTO(User user) {
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        userLoginResponseDTO.setId(user.getId());
        userLoginResponseDTO.setName(user.getName());
        userLoginResponseDTO.setEmail(user.getEmail());

        List<UserFriendResponseDTO> friendList = new ArrayList<>();
        for (int i=0; i<user.getFriends().size(); i++) {
            User friend = user.getFriends().get(i);
            friendList.add(toFriendDTO(friend));
        }
        userLoginResponseDTO.setFriendList(friendList);

        List<GroupResponseDTO> groups = new ArrayList<>();
        for (int i=0; i<user.getGroups().size(); i++) {
            Group group = user.getGroups().get(i);
            groups.add(toGroupDTO(group));
        }
        userLoginResponseDTO.setGroups(groups);
        return userLoginResponseDTO;
    }

    public static UserFriendResponseDTO toFriendDTO(User user) {
        UserFriendResponseDTO userFriendResponseDTO = new UserFriendResponseDTO();
        userFriendResponseDTO.setId(user.getId());
        userFriendResponseDTO.setName(user.getName());
        return userFriendResponseDTO;
    }

    public static GroupResponseDTO toGroupDTO(Group group) {
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        groupResponseDTO.setId(group.getId());
        groupResponseDTO.setName(group.getName());
        List<UserResponseDTO> members = new ArrayList<>();
        for (User user: group.getMembers()) {
            members.add(toUserDTO(user));
        }
        groupResponseDTO.setMembers(members);
        return groupResponseDTO;
    }

    public static UserResponseDTO toUserDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        return userResponseDTO;
    }
}