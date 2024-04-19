package com.ftninformatika.jwd.modul3.flowrSpot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Korisnik;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.KorisnikPromenaLozinkeDTO;

public interface KorisnikService {

    Optional<Korisnik> findOne(Long id);

    List<Korisnik> findAll();

    Page<Korisnik> findAll(int brojStranice);

    Korisnik save(Korisnik korisnik);

    void delete(Long id);

    Optional<Korisnik> findbyKorisnickoIme(String korisnickoIme);

    boolean changePassword(Long id, KorisnikPromenaLozinkeDTO korisnikPromenaLozinkeDto);
}
