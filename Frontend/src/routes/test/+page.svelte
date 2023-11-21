<script>
    import PostHoleForm from "../../components/PostHoleForm.svelte";
    let tester = {username:"ivara",gameId:15}
</script>
<main>
    <svelte:component this={PostHoleForm} {...tester}/>
</main>
