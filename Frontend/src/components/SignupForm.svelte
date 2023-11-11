<script>
    import {goto} from '$app/navigation';
    let backendRoute = 'http://localhost:8080/user';
    let password = '';
    let username = '';
    let salt = '';
    let name = '';
    async function signupPost(){
        const res = await fetch(
            backendRoute + '/signup',
            {method: 'POST',
            headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            username,
            password,
            salt,
            name
        })}
        )
        const json = await res.json();
        //console.log(json)
        if (json.success) {
            goto('../login')
        } else {
            
        }
    };


</script>

<h1>Welcome to Folf Tracker</h1>
<h2>Signup</h2>
<div class="form">
    <form action="">
        <input 
        bind:value={username}
        type="text"
        name="username"
        placeholder="Name"
        >
        <input 
        bind:value={name}
        type="text"
        name="Display name"
        placeholder="Display name"
        >
        <input 
        bind:value={password}
        type="text"
        name="password"
        placeholder="Password"
        >
        <input 
        on:click={signupPost}
        type="submit"
        value="Signup">
    </form>
    <a href="../login">
    <p>Back to login</p></a>
</div>