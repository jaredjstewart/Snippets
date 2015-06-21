package com.jaredjstewart.tree

def employees = [new MyObject(id: 'a'),
                 new MyObject(id: 'b', parentId: 'a'),
                 new MyObject(id: 'c', parentId: 'a'),
                 new MyObject(id: 'd', parentId: 'f'),
                 new MyObject(id: 'f', parentId: 'c')
]

def tree = TreeBuilder.buildTree(employees)


