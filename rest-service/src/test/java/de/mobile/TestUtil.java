package de.mobile;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public interface TestUtil {

    static ModelMapper modelMapper(){

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
