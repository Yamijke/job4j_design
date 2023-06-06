package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenRoletitleIsManager() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRoletitle()).isEqualTo("Manager");
    }

    @Test
    void whenAddAndFindRoleIsNull() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        Role result = store.findById("2");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindRoletitleIsManager() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        store.add(new Role("1", "Director"));
        Role result = store.findById("1");
        assertThat(result.getRoletitle()).isEqualTo("Manager");
    }

    @Test
    void whenReplaceThenRoleTitleIsDirector() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        store.replace("1", new Role("1", "Director"));
        Role result = store.findById("1");
        assertThat(result.getRoletitle()).isEqualTo("Director");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenReplaceOkThenTrue() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        boolean result = store.replace("1", new Role("1", "Director"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceOkThenFalse() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        boolean result = store.replace("2", new Role("1", "Director"));
        assertThat(result).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        Rolestore store = new Rolestore();
        store.add(new Role("1", "Manager"));
        boolean result = store.delete("1");
        assertThat(result).isTrue();
    }
}