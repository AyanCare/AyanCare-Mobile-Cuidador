package br.senai.sp.jandira.ayancare_frontmobile.retrofit.event

import br.senai.sp.jandira.ayancare_frontmobile.retrofit.event.service.Event

data class EventResponse(
    val status: Int,
    val evento: List<Event>
)
