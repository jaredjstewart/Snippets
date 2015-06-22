package com.jaredjstewart.tree

/**
 * Created by Jared on 6/21/2015.
 */
class TreeBuilder {

    static List<Node> buildTreeFirstTry(List<Employee> theEmployees) {
        Map<String, Node> employeeNodesByName = [:]
        List<Node> rootNodes = new LinkedList<>()

        for (Employee employee : theEmployees) {
            Node currentNode = new Node(employee: employee)
            employeeNodesByName.put((employee.id), currentNode)

            Node parentNode = employeeNodesByName.get(employee.parentId)

            if (parentNode) {
                parentNode.children.add(currentNode)

                List<Node> childNodesToAttach = rootNodes.findAll {
                    it.employee.parentId == employee.id
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

    static List<Node> buildTree(List<Employee> employees) {
        Map<String, Node> lookupTableOfNodesById = [:]
        List<Node> rootNodes = new LinkedList<Node>()

        for (Employee employee : employees) {
            Node ourNode = lookupTableOfNodesById.get(employee.id)
            if (ourNode) {   // was already found as a preliminary parent - register the actual object
                ourNode.employee = employee;
            } else {
                ourNode = new Node(employee: employee)
                lookupTableOfNodesById.put((employee.id), ourNode)
            }

            if (!employee.parentId) {
                //our node is a root node
                rootNodes.add(ourNode);
            } else {
                //our node is a child row - so we have a parent
                Node parentNode = lookupTableOfNodesById.get(employee.parentId)

                if (!parentNode) {   // unknown parent, construct preliminary parent
                    parentNode = new Node();
                    lookupTableOfNodesById.put((employee.parentId), parentNode);
                }
                parentNode.children.add(ourNode);
            }
        }

        return rootNodes;
    }
}
