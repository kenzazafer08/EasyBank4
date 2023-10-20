package dao;

import dto.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyI {
    Optional<Agency> add(Agency agency);
    Optional<Agency> SearchByCode(String code);
    Boolean delete(String code);
    Optional<Agency> update(Agency agency);
    Optional<Agency> SearchByAddress(String address);
    List<Agency> AgencyList();
}
