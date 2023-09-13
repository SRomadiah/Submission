package com.example.submission.data.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("gists_url")
	val gistsUrl: String? = null,

	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("following_url")
	val followingUrl: String? = null,

	@field:SerializedName("twitter_username")
	val twitterUsername: Any? = null,

	@field:SerializedName("bio")
	val bio: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("blog")
	val blog: String? = null,

	@field:SerializedName("subscriptions_url")
	val subscriptionsUrl: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("site_admin")
	val siteAdmin: Boolean? = null,

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@field:SerializedName("gravatar_id")
	val gravatarId: String? = null,

	@field:SerializedName("email")
	val email: Any? = null,

	@field:SerializedName("organizations_url")
	val organizationsUrl: String? = null,

	@field:SerializedName("hireable")
	val hireable: Any? = null,

	@field:SerializedName("starred_url")
	val starredUrl: String? = null,

	@field:SerializedName("followers_url")
	val followersUrl: String? = null,

	@field:SerializedName("public_gists")
	val publicGists: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("received_events_url")
	val receivedEventsUrl: String? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("events_url")
	val eventsUrl: String? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("name")
	val name: Any? = null,

	@field:SerializedName("location")
	val location: Any? = null,

	@field:SerializedName("node_id")
	val nodeId: String? = null
) : List<FollowersResponse> {
	override val size: Int
		get() = TODO("Not yet implemented")

	override fun contains(element: FollowersResponse): Boolean {
		TODO("Not yet implemented")
	}

	override fun containsAll(elements: Collection<FollowersResponse>): Boolean {
		TODO("Not yet implemented")
	}

	override fun get(index: Int): FollowersResponse {
		TODO("Not yet implemented")
	}

	override fun indexOf(element: FollowersResponse): Int {
		TODO("Not yet implemented")
	}

	override fun isEmpty(): Boolean {
		TODO("Not yet implemented")
	}

	override fun iterator(): Iterator<FollowersResponse> {
		TODO("Not yet implemented")
	}

	override fun lastIndexOf(element: FollowersResponse): Int {
		TODO("Not yet implemented")
	}

	override fun listIterator(): ListIterator<FollowersResponse> {
		TODO("Not yet implemented")
	}

	override fun listIterator(index: Int): ListIterator<FollowersResponse> {
		TODO("Not yet implemented")
	}

	override fun subList(fromIndex: Int, toIndex: Int): List<FollowersResponse> {
		TODO("Not yet implemented")
	}
}
