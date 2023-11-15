<script>
    import {goto,} from '$app/navigation';
    let backendRoute = 'http://localhost:8080/forum';
    let title = '';
    let userid = -1;
    let text = '';
    let failed = false;
    async function loginPost(){
        let postroute = backendRoute +'/newPost';
        const res = await fetch(
            postroute,
            {method: 'POST',
                headers: {"Content-Type": "application/json"},
                body: JSON.stringify({
                    title,text,userid
            })}
        );
        const json = await res.json();
        if(json.success){
            goto('../forum/readpost?id='+json.id);
        } else{
            failed = true;
        }
    }

    
</script>

<h1>Folf-Forum</h1>
<h2>Make a new thread</h2>
{#if failed}
    <p>post failed</p>
{/if}
<div class="form">
    <form action="">
        <input
        type="text"
        name="username"
        placeholder="user"
        >
        <input
        bind:value=
    </form>
</div>