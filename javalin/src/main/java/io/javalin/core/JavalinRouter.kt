package io.javalin.core

import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.http.Handler
import io.javalin.http.sse.SseClient
import io.javalin.websocket.WsConfig
import java.util.function.Consumer

interface Router {

    fun path(path: String): Router

    fun path(path: String, handler: Consumer<SubRouter>): Router

    /**
     * Adds a GET request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    operator fun get(path: String, handler: Handler): Router

    /**
     * Adds a POST request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun post(path: String, handler: Handler): Router

    /**
     * Adds a PUT request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun put(path: String, handler: Handler): Router

    /**
     * Adds a PATCH request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun patch(path: String, handler: Handler): Router

    /**
     * Adds a DELETE request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun delete(path: String, handler: Handler): Router

    /**
     * Adds a HEAD request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun head(path: String, handler: Handler): Router

    /**
     * Adds a OPTIONS request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun options(path: String, handler: Handler): Router

    // ********************************************************************************************
    // Secured HTTP verbs
    // ********************************************************************************************

    /**
     * Adds a GET request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    operator fun get(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    /**
     * Adds a POST request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun post(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    /**
     * Adds a PUT request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun put(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    /**
     * Adds a PATCH request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun patch(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    /**
     * Adds a DELETE request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun delete(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    /**
     * Adds a HEAD request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun head(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    /**
     * Adds a OPTIONS request handler with the given roles for the specified path to the instance.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [Handlers in docs](https://javalin.io/documentation.handlers)
     */
    fun options(path: String, handler: Handler, permittedRoles: Set<Role>): Router

    // ********************************************************************************************
    // Server-sent events
    // ********************************************************************************************

    /**
     * Adds a lambda handler for a Server Sent Event connection on the specified path.
     */
    fun sse(path: String, client: Consumer<SseClient>): Router

    /**
     * Adds a lambda handler for a Server Sent Event connection on the specified path.
     * Requires an access manager to be set on the instance.
     */
    fun sse(path: String, client: Consumer<SseClient>, permittedRoles: Set<Role>): Router

    // ********************************************************************************************
    // Before/after handlers (filters)
    // ********************************************************************************************

    /**
     * Adds a BEFORE request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.before-handlers)
     */
    fun before(path: String, handler: Handler): Router

    /**
     * Adds a BEFORE request handler for all routes in the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.before-handlers)
     */
    fun before(handler: Handler): Router

    /**
     * Adds an AFTER request handler for the specified path to the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.before-handlers)
     */
    fun after(path: String, handler: Handler): Router

    /**
     * Adds an AFTER request handler for all routes in the instance.
     *
     * @see [Handlers in docs](https://javalin.io/documentation.before-handlers)
     */
    fun after(handler: Handler): Router

    // ********************************************************************************************
    // WebSocket
    // ********************************************************************************************

    /**
     * Adds a WebSocket handler on the specified path.
     *
     * @see [WebSockets in docs](https://javalin.io/documentation.websockets)
     */
    fun ws(path: String, ws: Consumer<WsConfig>): Router

    /**
     * Adds a WebSocket handler on the specified path with the specified roles.
     * Requires an access manager to be set on the instance.
     *
     * @see AccessManager
     *
     * @see [WebSockets in docs](https://javalin.io/documentation.websockets)
     */
    fun ws(path: String, ws: Consumer<WsConfig>, permittedRoles: Set<Role>): Router

    /**
     * Adds a WebSocket before handler for the specified path to the instance.
     */
    fun wsBefore(path: String, wsConfig: Consumer<WsConfig>): Router

    /**
     * Adds a WebSocket before handler for all routes in the instance.
     */
    fun wsBefore(wsConfig: Consumer<WsConfig>): Router

    /**
     * Adds a WebSocket after handler for the specified path to the instance.
     */
    fun wsAfter(path: String, wsConfig: Consumer<WsConfig>): Router

    /**
     * Adds a WebSocket after handler for all routes in the instance.
     */
    fun wsAfter(wsConfig: Consumer<WsConfig>): Router

}

class SubRouter(private val app: Javalin, @Suppress("MemberVisibilityCanBePrivate") val basePath: String) :
    Router {

    override fun path(path: String): Router = SubRouter(app, this.basePath + path)

    override fun path(path: String, handler: Consumer<SubRouter>): Router {
        handler.accept(path(path) as SubRouter)
        return this
    }

    // Delegate other router methods to javalin
    override fun get(path: String, handler: Handler): Router {
        app.get(basePath + path, handler)
        return this
    }

    override fun get(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.get(basePath + path, handler, permittedRoles)
        return this
    }

    override fun post(path: String, handler: Handler): Router {
        app.post(basePath + path, handler)
        return this
    }

    override fun post(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.post(basePath + path, handler, permittedRoles)
        return this
    }

    override fun put(path: String, handler: Handler): Router {
        app.put(basePath + path, handler)
        return this
    }

    override fun put(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.put(basePath + path, handler, permittedRoles)
        return this
    }

    override fun patch(path: String, handler: Handler): Router {
        app.patch(basePath + path, handler)
        return this
    }

    override fun patch(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.patch(basePath + path, handler, permittedRoles)
        return this
    }

    override fun delete(path: String, handler: Handler): Router {
        app.delete(basePath + path, handler)
        return this
    }

    override fun delete(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.delete(basePath + path, handler, permittedRoles)
        return this
    }

    override fun head(path: String, handler: Handler): Router {
        app.head(basePath + path, handler)
        return this
    }

    override fun head(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.head(basePath + path, handler, permittedRoles)
        return this
    }

    override fun options(path: String, handler: Handler): Router {
        app.options(basePath + path, handler)
        return this
    }

    override fun options(path: String, handler: Handler, permittedRoles: Set<Role>): Router {
        app.options(basePath + path, handler, permittedRoles)
        return this
    }

    override fun sse(path: String, client: Consumer<SseClient>): Router {
        app.sse(basePath + path, client)
        return this
    }

    override fun sse(path: String, client: Consumer<SseClient>, permittedRoles: Set<Role>): Router {
        app.sse(basePath + path, client, permittedRoles)
        return this
    }

    override fun before(path: String, handler: Handler): Router {
        app.before(basePath + path, handler)
        return this
    }

    override fun before(handler: Handler): Router {
        app.before(basePath, handler)
        return this
    }

    override fun after(path: String, handler: Handler): Router {
        app.after(basePath + path, handler)
        return this
    }

    override fun after(handler: Handler): Router {
        app.after(basePath, handler)
        return this
    }

    override fun ws(path: String, ws: Consumer<WsConfig>): Router {
        app.ws(basePath + path, ws)
        return this
    }

    override fun ws(path: String, ws: Consumer<WsConfig>, permittedRoles: Set<Role>): Router {
        app.ws(basePath + path, ws, permittedRoles)
        return this
    }

    override fun wsBefore(path: String, wsConfig: Consumer<WsConfig>): Router {
        app.wsBefore(basePath + path, wsConfig)
        return this
    }

    override fun wsBefore(wsConfig: Consumer<WsConfig>): Router {
        app.wsBefore(basePath, wsConfig)
        return this
    }

    override fun wsAfter(path: String, wsConfig: Consumer<WsConfig>): Router {
        app.wsAfter(basePath + path, wsConfig)
        return this
    }

    override fun wsAfter(wsConfig: Consumer<WsConfig>): Router {
        app.wsAfter(basePath, wsConfig)
        return this
    }

}
