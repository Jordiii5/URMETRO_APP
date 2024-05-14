package com.example.urmetro.viewModel

import com.example.urmetro.model.Contacte

/**
 * Interfície per gestionar els esdeveniments de clics sobre elements de contactes.
 */
interface ContacteOnClick {

    /**
     * Funció cridada quan es fa clic sobre un contacte.
     *
     * @param contacte L'objecte Contacte associat amb l'element clicat.
     */
    fun onClickContacte(contacte: Contacte)
}