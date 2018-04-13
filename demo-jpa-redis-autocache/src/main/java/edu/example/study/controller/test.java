package edu.example.study.controller;

import edu.example.study.entity.User;
import org.springframework.data.jpa.repository.query.PartTreeJpaQuery;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.PartTree;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Taxz on 2018/4/9.
 */
public class test {
    public static void main(String [] args){
        //JpaRepositoryFactory

        String eg ="";
       eg = "countByAge";

        PartTree tree = new PartTree(eg, User.class);
        Iterator<PartTree.OrPart> it = tree.iterator();
        while (it.hasNext()) {
            PartTree.OrPart node = it.next();
            System.out.println(node.toString()+"01");
            Iterator<Part> op = node.iterator();
            while (op.hasNext()) {
                Part part = op.next();
                System.out.println(part.toString()+"0101");
            }
        }
        tree.isCountProjection();
        //System.out.println(tree.toString());
    }
    public static void maine(String args[]){
       /* Pattern PREFIX_TEMPLATE = Pattern.compile("^(find|read|get|query|stream|count|exists|delete|remove)((\\p{Lu}.*?))By");
        Matcher matcher = PREFIX_TEMPLATE.matcher("findUUuwwwBy");
        System.out.println(matcher.find());*/
        String s = StringUtils.uncapitalize("ABC");
        System.out.println(s);
    }
}
