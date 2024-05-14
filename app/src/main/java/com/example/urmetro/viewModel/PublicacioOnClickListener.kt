package com.example.urmetro.viewModel

import com.example.urmetro.model.Publicacions

/**
 * Interfície per gestionar els esdeveniments de clic en les publicacions.
 */
interface PublicacioOnClickListener {
    /**
     * Es crida quan es fa clic en una publicació.
     * @param post La publicació en la qual s'ha fet clic.
     */
    fun onClickPublicacio(post: Publicacions)

}