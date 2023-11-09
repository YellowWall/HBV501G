<script>
    let backendRoute = 'http://localhost:8080/user';
    let password = '';
    let username = '';
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
        console.log(json)

        let result = JSON.stringify(json);
        //console.log(result)
    };

    async function getUsers(){
        const res = await fetch(
            backendRoute + '/users',
            {method: 'GET'} 
        )
        const json = await res.json();
        let result = JSON.stringify(json);
        //console.log(result);
    }
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
        bind:value={password}
        type="text"
        name="password"
        placeholder="Password"
        >
        <input 
        on:click={loginPost}
        type="submit"
        value="Signup">
    </form>
</div>