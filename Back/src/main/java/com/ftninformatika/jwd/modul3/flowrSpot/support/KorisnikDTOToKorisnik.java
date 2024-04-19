package com.ftninformatika.jwd.modul3.flowrSpot.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Korisnik;
import com.ftninformatika.jwd.modul3.flowrSpot.service.KorisnikService;
import com.ftninformatika.jwd.modul3.flowrSpot.web.dto.KorisnikDTO;	

@Component
public class KorisnikDTOToKorisnik implements Converter<KorisnikDTO, Korisnik> {

    @Autowired
    private KorisnikService korisnikService;

    @Override
    public Korisnik convert(KorisnikDTO korisnikDTO) {
        Korisnik korisnik = null;
        if(korisnikDTO.getId() != null) {
            korisnik = korisnikService.findOne(korisnikDTO.getId()).get();
        }

        if(korisnik == null) {
            korisnik = new Korisnik();
        }

        korisnik.setKorisnickoIme(korisnikDTO.getKorisnickoIme());
        korisnik.seteMail(korisnikDTO.geteMail());
        korisnik.setIme(korisnikDTO.getIme());
        korisnik.setPrezime(korisnikDTO.getPrezime());

        return korisnik;
    }

}
