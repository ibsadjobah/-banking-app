package com.bank_app.dto;


import com.bank_app.models.Address;
import com.bank_app.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class AddressDto {

    private Integer id;

    private String street;

    private Integer houseNumber;

    private Integer zipCode;

    private String city;

    private String country;

    private Integer userId;


    public static  AddressDto fromEntity(Address address)
    {
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();
    }


    public static Address toEntity(AddressDto address)
    {
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .user(User.builder()
                        .id(address.getUserId())
                        .build()
                )
                .build();
    }


}
