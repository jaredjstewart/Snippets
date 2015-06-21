package com.jaredjstewart.tree

/**
 * Created by Jared on 6/21/2015.
 */
class TreeBuilder {

    static List<Node> buildTreeFirstTry(List<MyObject> theEmployees) {
        Map<String, Node> employeeNodesByName = [:]
        List<Node> rootNodes = new LinkedList<>()

        for (MyObject employee : theEmployees) {
            Node currentNode = new Node(source: employee)
            employeeNodesByName.put((employee.id), currentNode)

            Node parentNode = employeeNodesByName.get(employee.parentId)

            if (parentNode) {
                parentNode.children.add(currentNode)

                List<Node> childNodesToAttach = rootNodes.findAll {
                    it.source.parentId == employee.id
                }

                for (Node child : childNodesToAttach) {
                    rootNodes.remove(child)
                    currentNode.children.add(child)
                }
            } else {
                rootNodes.add(currentNode)
            }


        }

        return rootNodes
    }

    static List<Node> buildTree(List<MyObject> actualObjects) {
        Map<String, Node> lookup = [:]
        List<Node> rootNodes = new LinkedList<Node>()

        for (MyObject item : actualObjects) {
            // add us to lookup
            Node ourNode = lookup.get(item.id)

            Node parentNode = lookup.get(item.parentId)

            if (ourNode) {   // was already found as a preliminary parent - register the actual object
                ourNode.source = item;
            } else {
                ourNode = new Node(source: item)
                lookup.put((item.id), ourNode)
            }

            // hook into parent
            if (!item.parentId) {
                // is a root node
                rootNodes.add(ourNode);
            } else {
                // is a child row - so we have a parent
                if (!parentNode) {   // unknown parent, construct preliminary parent
                    parentNode = new Node();
                    lookup.put((item.parentId), parentNode);
                }
                parentNode.children.add(ourNode);
            }
        }

        return rootNodes;
    }
}
