package com.codekopf.itemmanagement.domain.service;

import com.codekopf.itemmanagement.domain.model.Colour;
import com.codekopf.itemmanagement.infrastructure.entity.ColourEntity;
import com.codekopf.itemmanagement.infrastructure.repository.ColourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public final class ColourServiceImpl implements ColourService {

    private final ColourRepository colourRepository;

    @Autowired
    public ColourServiceImpl(final ColourRepository colourRepository) {
        this.colourRepository = colourRepository;
    }

    public List<Colour> getAllColours() {
        return colourRepository.findAll().stream().map(ColourEntity::convertToDomainObject).toList();
    }

    public Optional<Colour> getColourById(final UUID id) {
        return colourRepository.findById(id).map(ColourEntity::convertToDomainObject);
    }

    // TODO add description - performs save and merge(update) if (id will work)
    public Colour saveColour(final Colour colour) {
        return colourRepository.save(new ColourEntity(colour)).convertToDomainObject();
    }

    public void deleteColour(final UUID id) {
        colourRepository.deleteById(id);
    }

}
