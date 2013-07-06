package com.objectpartners.note

class Note {

    String name
    String content
    Date dateCreated

    static constraints = {
        name nullable: false, unique: true
        content nullable: true
    }
}
