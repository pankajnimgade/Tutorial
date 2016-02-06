package stack.overflow;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

/**
 * Created by Pankaj Nimgade on 05-02-2016.
 */
public class TestDrive {


    public static void main(String[] args){



    }
}

@Root( name = "scenario", strict = false)
class Scenario{
    @Attribute(name = "name")
    private String name;

    @ElementList(name = "cmd", inline = true)
    private ArrayList<Cmd> cmds;

    public String getName() {
        return name;
    }

    public ArrayList<Cmd> getCmds() {
        return cmds;
    }
}

@Root (name = "cmd", strict = false)
class Cmd{
    @Attribute(name = "name")
    private String name;

    @ElementList(name = "return", inline = true)
    private ArrayList<String> lists;

    public String getName() {
        return name;
    }

    public ArrayList<String> getLists() {
        return lists;
    }
}



