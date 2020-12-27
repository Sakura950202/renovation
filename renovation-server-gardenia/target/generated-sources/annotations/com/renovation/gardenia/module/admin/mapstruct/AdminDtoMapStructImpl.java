package com.renovation.gardenia.module.admin.mapstruct;

import com.renovation.gardenia.module.admin.dto.AdminDto;
import com.renovation.gardenia.module.admin.entity.Admin;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-27T14:44:02+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"
)
@Component
public class AdminDtoMapStructImpl implements AdminDtoMapStruct {

    @Override
    public Admin toEntity(AdminDto o) {
        if ( o == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setId( o.getId() );
        admin.setName( o.getName() );

        return admin;
    }

    @Override
    public List<Admin> toEntityList(List<AdminDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Admin> list1 = new ArrayList<Admin>( list.size() );
        for ( AdminDto adminDto : list ) {
            list1.add( toEntity( adminDto ) );
        }

        return list1;
    }

    @Override
    public AdminDto toModel(Admin e) {
        if ( e == null ) {
            return null;
        }

        AdminDto adminDto = new AdminDto();

        adminDto.setId( e.getId() );
        adminDto.setName( e.getName() );

        return adminDto;
    }

    @Override
    public List<AdminDto> toModelList(List<Admin> list) {
        if ( list == null ) {
            return null;
        }

        List<AdminDto> list1 = new ArrayList<AdminDto>( list.size() );
        for ( Admin admin : list ) {
            list1.add( toModel( admin ) );
        }

        return list1;
    }
}
