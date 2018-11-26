package be.ucll.cityquest.games;

import be.ucll.cityquest.games.model.Coordinates;
import be.ucll.cityquest.games.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;

public class JpaConverter {


    public static class ForCoordinates extends AbstractConverter implements AttributeConverter<Coordinates, String> {

        @Override
        public String convertToDatabaseColumn(Coordinates attribute) {
            return serialize(attribute);
        }

        @Override
        public Coordinates convertToEntityAttribute(String dbData) {
            return deserialize(dbData, Coordinates.class);
        }
    }

    public static class ForQuestionList extends AbstractConverter implements AttributeConverter<List<Question>, String> {

        @Override
        public String convertToDatabaseColumn(List<Question> attribute) {
            return serialize(attribute);
        }

        @Override
        public List<Question> convertToEntityAttribute(String dbData) {
            return deserialize(dbData, collectionType(List.class, Question.class));
        }
    }

    public static abstract class AbstractConverter {
        private final static ObjectMapper objectMapper = new ObjectMapper();

        protected String serialize(Object obj) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        protected <T extends Object> T deserialize(String valueAsString, Class<T> clazz) {
            try {
                return objectMapper.readValue(valueAsString, clazz);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        protected <T extends Object> T deserialize(String valueAsString, JavaType javaType) {
            try {
                return objectMapper.readValue(valueAsString, javaType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        protected JavaType collectionType(Class collectionClass, Class instanceClass) {
            return objectMapper.getTypeFactory().constructCollectionType(collectionClass, instanceClass);
        }
    }

}

