

<script>
    import {goto,} from '$app/navigation';
    let backendRoute = 'http://localhost:8080/user';
    let password = '';
    let username = '';
    let failed = false;
    async function loginPost(){
        const res = await fetch(
            backendRoute + '/login',
            {method: 'POST',
            headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            username,
            password
        })}
        )
        const json = await res.json();
        //console.log(json)
        if (json.success) {
            goto('../homepage');
        } else {
            failed = true;
        }

    };

</script>
<h1>Welcome to Folf Tracker</h1>
<h2>Login</h2>
{#if failed}
    <p>Wrong username or password</p>
{/if}
<div class="form">
    <form action="">
        <input 
        bind:value={username}
        type="text"
        name="username"
        placeholder="Username"
        >
        <input 
        bind:value={password}
        type="text"
        name="password"
        placeholder="Password"
        >
        <input 
        on:click={loginPost}
        type="submit"
        value="Login">
    </form>
    <a href="../signup">
    <p>Signup for Folf tracker</p></a>
</div>


