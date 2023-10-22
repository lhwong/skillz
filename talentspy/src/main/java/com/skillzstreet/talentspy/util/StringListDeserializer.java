package com.skillzstreet.talentspy.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class StringListDeserializer extends JsonDeserializer<List<String>>{

    @Override
    public List<String> deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        List<String> ret = new ArrayList<>();

        ObjectCodec codec = parser.getCodec();
        TreeNode node = codec.readTree(parser);

        if (node.isArray()){
            for (JsonNode n : (ArrayNode)node){
                ret.add(n.asText());
            }
        } else if (node.isValueNode()){
            ret.add( ((JsonNode)node).asText() );
        }
        return ret;
    }
}